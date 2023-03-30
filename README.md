## Table of Content
[Run Locally Using Docker](#run-locally-using-docker)

[Local Development](#local-development)

[Deploy to AWS](#deploy-to-aws)

## Run Locally Using Docker

```bash
# Build JARs
cd backend
./mvnw clean package -DskipTests
cd ..

# Build images
docker compose -f docker-compose.local-mysql-ui.yml build

# Run containers
docker compose -f docker-compose.local-mysql-ui.yml up -d
```

Wait until MySQL container ready, then enter MySQL container using:
```bash
docker exec -it awsdemo-mysql bash
```

Inside the container, login to MySQL database:
```bash
mysql -u root -p$MYSQL_ROOT_PASSWORD awsdemo
```

Populate `item` table:
```sql
TRUNCATE TABLE item;

INSERT INTO item (sku, description, qty, image_url) VALUES
('ZTE-MF293N','Orbit Star Z1', 100,'http://localhost:8080/images/ZTE-MF293N.png'),
('NOTION-M21','Orbit MiFi N1', 100,'http://localhost:8080/images/NOTION-M21.png'),
('NOTION-M22','Orbit MiFi N2', 100,'http://localhost:8080/images/NOTION-M22.png'),
('HUAWEI-B311B-853','Orbit Star H1', 100,'http://localhost:8080/images/HUAWEI-B311B-853.png'),
('NOTION-R281','Orbit Pro', 100,'http://localhost:8080/images/NOTION-R281.png'),
('NOTION-HKM0128-A','Orbit Star N2', 100,'http://localhost:8080/images/NOTION-HKM0128-A.png'),
('NOTION-R0127A','Orbit Star N1', 100,'http://localhost:8080/images/NOTION-R0127A.png'),
('ZTE-5G-MC801A','Orbit Turbo 5G', 100,'http://localhost:8080/images/ZTE-5G-MC801A.png'),
('HUAWEI-B628-350','Orbit Max H1', 100,'http://localhost:8080/images/HUAWEI-B628-350.png'),
('ADVAN-ZLTA10','Orbit Star A1', 100,'http://localhost:8080/images/ADVAN-ZLTA10.png')
;

-- Use CTRL+D to exit
```

Now, open the UI on http://localhost:3000

To stop and destroy containers:
```bash
docker compose -f docker-compose.local-mysql-ui.yml down -v
```

## Local Development

Prepare database locally
Using Docker:
```bash
docker compose -f docker-compose.mysql.yml up -d
```

Backend server
```bash
cd backend

# Run
./mvnw spring-boot:run

# Build WAR
./mvnw clean package

# Run backend server using WAR:
java -jar target/backend-0.0.1-SNAPSHOT.war
```

Frontend client:
```bash
cd frontend

# Build
yarn build

# Run server
serve -s build/
```

## Deploy to AWS

### 1 Create RDS Database

### 2 Build and Upload Image to ECR
```bash
cd backend

# Build using profile aws which calls for application-aws.properties
./mvnw clean package -DskipTests

cd ..

aws ecr get-login-password --region ap-southeast-1 | docker login --username AWS --password-stdin 880070412515.dkr.ecr.ap-southeast-1.amazonaws.com

docker compose -f docker-compose.local-mysql-ui.yml build

docker tag aws-ignite-backend:latest 880070412515.dkr.ecr.ap-southeast-1.amazonaws.com/aws-ignite-backend:latest

docker push 880070412515.dkr.ecr.ap-southeast-1.amazonaws.com/aws-ignite-backend:latest
```

### 3 Create ECS Cluster
### 4 Create Task Definition
### 5 Run Task

### 6 Upload Assets To S3
```bash
cd frontend
# build
REACT_APP_API_URL=http://aws-ignite-backend-alb-560077133.ap-southeast-1.elb.amazonaws.com/api yarn build

# upload assets
cd build
aws s3 sync . s3://tole-2515-static-public/
```

### 7 Populate `item` table:
```sql
TRUNCATE TABLE item;

INSERT INTO item (sku, description, qty, image_url) VALUES
('ZTE-MF293N','Orbit Star Z1', 100,'/images/ZTE-MF293N.png'),
('NOTION-M21','Orbit MiFi N1', 100,'/images/NOTION-M21.png'),
('NOTION-M22','Orbit MiFi N2', 100,'/images/NOTION-M22.png'),
('HUAWEI-B311B-853','Orbit Star H1', 100,'/images/HUAWEI-B311B-853.png'),
('NOTION-R281','Orbit Pro', 100,'/images/NOTION-R281.png'),
('NOTION-HKM0128-A','Orbit Star N2', 100,'/images/NOTION-HKM0128-A.png'),
('NOTION-R0127A','Orbit Star N1', 100,'/images/NOTION-R0127A.png'),
('ZTE-5G-MC801A','Orbit Turbo 5G', 100,'/images/ZTE-5G-MC801A.png'),
('HUAWEI-B628-350','Orbit Max H1', 100,'/images/HUAWEI-B628-350.png'),
('ADVAN-ZLTA10','Orbit Star A1', 100,'/images/ADVAN-ZLTA10.png')
;

-- Use CTRL+D to exit
```