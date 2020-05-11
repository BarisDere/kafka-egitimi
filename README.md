# kafka-egitimi

### Projenin anlatıldığı youtube videom:  https://www.youtube.com/watch?v=Ej-HcZd-Alo

### Set up

    mvn clean install

    docker-compose up --build -d

### Create Customer

    -XPOST http://localhost:8080/customers
    {
	     "id" : 7584,
	     "firstName": "Baris",
	     "lastName": "Dere",
	     "metaInformation" : "test5 test6"
    }
    
### Search elasticsearch 

    http://localhost:9200/systemindex/_search    
