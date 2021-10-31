# Deployment Process Instructions

Follow these instructions to deploy the latest build

## On Development machine
1. Create the target JAR file for deployment using maven (from main branch): `mvn clean package`
2. Build the deployment container using docker `buildx` (I use buildx to make sure the container runs on amd64)
   - Note: This is the process that uses the `Dockerfile`

```
docker buildx build --platform linux/amd64 --push -t steve763/deployment .
```

## On deployment server (EC2 instance)
1. Pull the latest image from the image repository: `docker pull steve763/deployment`
2. Stop the old container `docker stop steve-dev-blog`
3. Remove the old container, so you can reuse the name: `docker rm steve-dev-blog`
4. Start up the latest image:
   -Note: docker exposes the server on port 8080, Nginx proxies this connection to 80 and 443 for external connection
```
docker run -p8080:8080 -d --name steve-dev-blog steve763/deployment
```
5. Go to [stevedevblog](https://stevedevblog.com) to check the webserver is running and the new deployment is live!

I am interested in trying to make this less manual work, however my main priority right now 
is adding features to the site. The current process works as an MVP of the deployment process.

The first and easiest thing I can do is to write a simple script on the EC2 instance to pull the
latest image, stop and remove the old build and start up the new one. Which I will get around to
next.