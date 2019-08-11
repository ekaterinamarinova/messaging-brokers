# producers
## Producers and configurations of different messaging platforms

## In order to start the application you need to do the following:

- In order to send messages using Kafka:
1. Install Kafka and start the service locally on port 9092
2. Run the runKafka script OR change the spring profile in application.properties to 'kafka'

- In order to send messages using RabbitMQ:
1 . Install RabbitMQ and enable the service locally on port 5672
2 . (OPTIONAL) Enable RabbitMQ's real-time admin on port 15672 in order to easily follow the messages flow and create and bind exchanges/queues
3 . Run the runRabbit script OR change the spring profile in application.properties to 'rabbit'
