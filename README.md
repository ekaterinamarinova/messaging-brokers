# producers
## Producers and configurations of different messaging platforms

### In order to start the application you need to do the following:

- In order to send messages using Kafka:
1. Install Kafka and start the service locally on port 9092
2. Run the runKafka script OR change the spring profile in application.yml to 'kafka'

- In order to send messages using RabbitMQ:
1 . Install RabbitMQ and enable the service locally on port 5672
2 . (OPTIONAL) Enable RabbitMQ's real-time admin on port 15672 in order to easily follow the messages flow and create and bind exchanges/queues
3 . Run the runRabbit script OR change the spring profile in application.yml to 'rabbit'

- In order to send messages using ArtemisMQ:
1 . Install the ActiveMQ Artemis broker and run it locally
2 . (OPTIONAL) Use Artemis' admin page on http://localhost:8161/console/login in order to create queues/messages
3 . Run the runArtemis script OR change the spring profile in application.yml to 'artemis'

## Dependency versions:

- Spring Boot - 2.1.6 RELEASE
- RabbitMQ    - 3.7.15
- Kafka       - 2.3.0 RELEASE
- Artemis     - 2.9.0
