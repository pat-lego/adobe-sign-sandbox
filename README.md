# adobe-sign-sandbox
An adobe sign API testing repository

# oAuth Implementation

oAuth token exchange between the client and the server requires the following steps to be created.

## Get Request to retrieve Code
Once performed correctly this API call will generate a code that is valid for 5 minutes.

```
https://aftia-solutions.na1.echosign.com/public/oauth?redirect_uri=https://example.com/oauthDemo&response_type=code&client_id=d4HQNPFIXFD255H&scope=user_login:self+agreement_write:account&state=S6YQD7KDA556DIV6NAU4ELTGSIV26ZNMXDSF7WIEEP0ZLQCLDQ89OYG78C3K9SROC8DXCGRVSGKU1IT1
```

Once the code is generated users can then copy this code and use it to generate an access_token that will be used to invoke Adobe Sign API's

## oAuth Token Request

Now that the code has been retrieved the user can use it to get a access_token. Perform a POST request to the following URL `https://api.na1.echosign.com/oauth/token`

POST Method Type: `x-www-form-urlencoded`
Post Body:
1. `grant_type`: `authorization_code`
2. `client_id`: `Retrieved from Application`
3. `client_secret`: `Retrieved from Application`
4. `code`: `Retrieved from the previous step`
5. `redirect_uri`: `Retrieved from Application`

Returns the following JSON payload
```
{
    "access_token": ".....",
    "refresh_token": ".....",
    "token_type": "Bearer",
    "expires_in": 3600
}
```

## oAuth Token Refresh

The access_token lasts 5 minutes before it expires so we can refresh it if we need a new one in order to prevent API request errors. Perform a POST request to the following URL `https://api.na1.echosign.com/oauth/refresh`

POST Method Type: `x-www-form-urlencoded`
Post Body:
1. `grant_type`: `refresh_token`
2. `client_id`: `Retrieved from Application`
3. `client_secret`: `Retrieved from Application`
4. `refresh_token`: `Retrieved from the previous step`

Returns the following JSON payload
```
{
    "access_token": ".....",
    "token_type": "Bearer",
    "expires_in": 3600
}
```

# Q&A

1. What's the difference between a transient document and a library document?

Answer: Transient documents can only be referenced for 7 days and then are deleted from the Adobe Sign document cloud. Library documents can be referenced whenever, they are never deleted from the Document Cloud library.

2. What if an agreement that refers to a transient document lasts longer then 7 days?

Answer: Agreements do not refer to the transient document directly, instead they refer to a copy of a transient document.

3. What's the difference between MegaSign and GigaSign?

| Differences                           | MegaSign                                 | GigaSign                                                       |
|---------------------------------------|------------------------------------------|----------------------------------------------------------------|
| Number of agreements that can be sent | 300                                      | 1500                                                           |
| Can submit different documents        | No always the same document              | Yes, can send different document templates                     |
| Can submit to various signers         | No only one recipient                    | Yes can submit to various recipients in parallel or sequential |
| Is managed in the cloud               | Yes through the sign dashboard           | No, managed by the client using a runnable JAR file            |
| Input data format                     | CSV                                      | CSV                                                            |
| Can view who signed the agreement     | Yes, can view individuals who have signed the agreement as well as nudge the others who have not | Yes, can view the individuals who have signed the agreement |

4. What's a webform in Adobe Sign? 

Answer: A web form is an agreement that does not an initial signer. Instead a URL is given to a user who can then put the link on their website. The viewer who then fills out the form and submits it becomes the first signer in the signature process. Proceeding users may follow once the webform is signed. Users can create a webform on the Adobe Sign splash page.

5. Can Adobe Sign agreements contain file attachments?

Yes, there is a file attachment component that will convert all attachments to the PDF and add it to the signed agreement.