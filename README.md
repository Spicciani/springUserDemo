# springUserDemo

Create Docker Network  
docker network create user_demo_net  


MySql Docker container creation:  
docker run --network user_demo_net --detach --env MYSQL_ROOT_PASSWORD=dummypassword --env MYSQL_USER=user-demo --env MYSQL_PASSWORD=dummypassword --env MYSQL_DATABASE=user-demo-database --name UserDemoMySql --publish 3306:3306 mysql:8-oracle  

Maven Project container creation:
docker run --network user_demo_net --name user-demo-rest -p8080:8080 user-demo:0.0.1-SNAPSHOT


Swagger enabled  
http://localhost:8080/swagger-ui
