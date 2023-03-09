mvn clean spring-boot:build-image -Dspring-boot.build-image.imageName=bonifacechacha/zhcc-ix:latest -DskipTests
docker push bonifacechacha/zhcc-ix:latest