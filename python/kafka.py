from kafka import KafkaConsumer

consumer = KafkaConsumer(
    'customer_analysis_topic',
     bootstrap_servers=['kafka-1:9092'],
     enable_auto_commit=True,
     group_id='my-group-1',
     value_deserializer=lambda m: m.decode('utf-8'),
     key_deserializer=lambda m: m.decode('utf-8'))

print('Start listening')

for message in consumer:
    value = message.value
    key = message.key
    print('Mesaj okundu: {}: {}'.format(key, value))