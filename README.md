## Local Development
### Develop
Prepare database locally
Using Docker:
```bash
docker compose -f docker-compose.mysql.yml up -d
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
('ADVAN-ZLTA10','Orbit Star A1', 100,'http://localhost:8080/images/ADVAN-ZLTA10.png'),
('ZTE-MF283U','Orbit Star 3', 100,'http://localhost:8080/images/ZTE-MF283U.png'),
('ZTE-MF286R','Orbit Pro 2', 100,'http://localhost:8080/images/ZTE-MF286R.png')
;
```

Run server
```bash
./mvnw spring-boot:run
```

Build WAR
```bash
./mvnw clean package
```

Run WAR:
```bash
java -jar target/backend-0.0.1-SNAPSHOT.war
```

Run frontend client:
```bash
cd frontend
yarn start
```

### Build Locally
Backend server:
```bash
cd backend

# Create WAR
./mvnw clean package

# Run server
java -jar target/awsdemo-0.0.1-SNAPSHOT.war
```

Frontend:
```bash
cd frontend

# Build
yarn build

# Run server
serve -s build/
```
