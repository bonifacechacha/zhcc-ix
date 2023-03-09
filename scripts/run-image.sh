docker run \
-p 8080:8080 \
--name zhcc-ix \
--network proxy \
-e zhcc.token-url=http://pcmt-reverse-proxy/api/oauth/v1/token \
-e zhcc.api-url=http://pcmt-reverse-proxy/api/rest/v1 \
--detach \
bonifacechacha/zhcc-ix