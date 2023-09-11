# springUserDemo

Create Docker Network  
docker network create user_demo_net  


MySql Docker container creation:  
docker run --name UserDemoMySql --network user_demo_net -p 3309:3306 -e MYSQL_USER=user-demo -e MYSQL_PASSWORD=dummypassword -e MYSQL_ROOT_PASSWORD=dummypassword -e MYSQL_DATABASE=user-demo-database -d mysql:8-oracle  

Maven Project container creation:

Create image  
docker build --tag user-demo:latest .  

Create container   
docker run --network user_demo_net -p 9090:8080 -e MYSQL_HOST=UserDemoMySql -e MYSQL_PORT=3309 -d user-demo:latest


Swagger enabled  
http://localhost:8080/swagger-ui
