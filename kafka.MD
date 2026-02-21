-- create kafka cluster
docker run --rm -d --network kafka-net -p 2181:2181 -p 3030:3030 -p 9092:9092 -p 8081:8081 -p 8082:8082 -e ADV_HOST=kafka-cluster --name kafka-cluster lensesio/fast-data-dev

