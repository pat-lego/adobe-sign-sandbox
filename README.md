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
`grant_type`: `authorization_code`
`client_id`: `Retrieved from Application`
`client_secret`: `Retrieved from Application`
`code`: `Retrieved from the previous step`
`redirect_uri`: `Retrieved from Application`

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
`grant_type`: `refresh_token`
`client_id`: `Retrieved from Application`
`client_secret`: `Retrieved from Application`
`refresh_token`: `Retrieved from the previous step`

Returns the following JSON payload
```
{
    "access_token": ".....",
    "token_type": "Bearer",
    "expires_in": 3600
}
```
