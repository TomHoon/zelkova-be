### 도커 컨테이너, 이미지 이미 존재한다면 아래를 먼저 진행해야됨

- docker stop zelkova-container
- docker rm zelkova-container
- docker rmi zelkova-image

### 빌드

- ./gradlew clean
- ./gradlew build -x test

### docker image 만들기

- docker build -t zelkova-image .

### docker 실행

- docker run -d -p 26000:26000 --network chatting-network --name zelkova-container zelkova-image
