package io.github.adobe.sign.impl.auth;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import io.github.adobe.sign.core.auth.Authenticated;
import io.github.adobe.sign.core.auth.CredentialLoader;
import io.github.adobe.sign.core.auth.Credentials;
import io.github.adobe.sign.core.auth.SignAuth;
import io.github.adobe.sign.core.auth.SignAuthException;
import io.github.adobe.sign.core.metadata.SignMetadata;

public class BasicSignAuth implements SignAuth {

    @Override
    public Authenticated refresh(CredentialLoader credentials, SignMetadata metadata) throws SignAuthException {
        try (CloseableHttpClient httpclient = HttpClients.createDefault()) {
            Credentials creds = credentials.loadCredentials(null);

            List<NameValuePair> form = new ArrayList<>();
            form.add(new BasicNameValuePair(CredentialLoader.CLIENT_ID, creds.getClientId()));
            form.add(new BasicNameValuePair(CredentialLoader.CLIENT_SECRET, creds.getClientSecret()));
            form.add(new BasicNameValuePair(CredentialLoader.REFRESH_TOKEN, creds.getRefreshToken()));
            form.add(new BasicNameValuePair(GRANT_TYPE, REFRESH_TOKEN_GT));

            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(form, Consts.UTF_8);

            HttpPost httpPost = new HttpPost(SignAuth.REFRESH_URL);
            httpPost.setEntity(entity);

            // Create a custom response handler
            ResponseHandler<Authenticated> responseHandler = response -> {
                Authenticated authenticated = new Authenticated();
                int status = response.getStatusLine().getStatusCode();
                if (status >= 200 && status < 300) {
                    HttpEntity responseEntity = response.getEntity();
                    if (responseEntity != null) {
                        JsonObject payload = JsonParser.parseString(EntityUtils.toString(responseEntity)).getAsJsonObject();
                        authenticated.setAccessToken(payload.get(ACCESS_TOKEN).getAsString());
                        authenticated.setRefreshToken(creds.getRefreshToken());
                        authenticated.setCreated(LocalDateTime.now());
                        authenticated.setExpiresIn(payload.get(EXPIRES_IN).getAsInt());
                        authenticated.setTokenType(payload.get(TOKEN_TYPE).getAsString());
                        return authenticated;
                    } else {
                        throw new ClientProtocolException("Unexpected response status: " + status);
                    }
                } else {
                    throw new ClientProtocolException("Unexpected response status: " + status);
                }
            };
            return httpclient.execute(httpPost, responseHandler);
        } catch (Exception e) {
            throw new SignAuthException(e.getMessage(), e);
        }
    }

    @Override
    public Boolean isValidAuth(Authenticated authenticated) throws SignAuthException {
        if (authenticated == null) {
            return Boolean.FALSE;
        }

        if (authenticated.getCreated().plusSeconds(authenticated.getExpiresIn()).compareTo(LocalDateTime.now()) <= 0) {
            return Boolean.FALSE;
        }

        return Boolean.TRUE;
    }

}
