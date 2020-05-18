# Kafka Eğitimi bitirme projesi
Apache Kafka eğitim serisinin bugüne kadar öğrendiğimiz her şeyin tatbik edilerek Java Scala Python dilleri kullanılarak açıklandığı, bunun yanında Spring Boot, Spring Kafka, Kafka Streams, Akka Streams, Elasticsearch, Postman, Docker, Docker compose gibi teknolojilerin kullanıldığı bir kodlama projesi olmuştur.

### Bu proje Youtube kanalımdaki Kafka Eğitiminin bitirme projesidir. Eğitime gitmek için:
https://www.youtube.com/playlist?list=PLZYKO7600KN9ttvG0mFYzP82AbIdeJYwq

### Projenin anlatıldığı youtube videom:  
https://www.youtube.com/watch?v=Ej-HcZd-Alo

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
