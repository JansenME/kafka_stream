This application creates a stream using Kafka.

To use this application, you need to have Zookeeper and Kafka installed on your local machine. Check a guide to do this over here: https://kafka.apache.org/quickstart

After this, you also need to start a topic called 'AccountsBalancesList' (check step 3 in the guide)

To test this application, follow the next steps:
- Start the application in your IDE
- Go to http://localhost:8080/consume to start consuming the stream
- Go to http://localhost:8080/ (in another tab) and enter a number
- Click Go and check the Terminal in your IDE
- You will see the generated data been written to the Terminal

**Plans:**
- Package Zookeeper and Kafka in the application
- Stream the data to the frontend