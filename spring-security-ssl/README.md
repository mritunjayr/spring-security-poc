# spring boot integration ssl certificate

This is demo POC for adding ssl certificates to your application to secure each http request and response.


this is example command to create ssl self assigned certificate for dev purpose:-

`keytool -genkeypair -alias bootsecurity -keyalg RSA -keysize 2048 -storetype PKCS12 -keystore bootsecurity.p12 -validity 3650`
