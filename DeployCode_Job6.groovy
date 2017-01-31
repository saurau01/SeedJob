freeStyleJob('OneClickDeployment/DeployCode_Job6') {
      
steps {
shell(''' 
#!/bin/sh
export HOME=/root
work_space=/Users/Shared/Jenkins/Home/workspace/OneClickDeployment/Deploy_Job4
IP=localhost:8080
API_TOKEN=d1e3c81ca8cf0f2ac074fc9ccde17fc2
cd $work_space

server=`vagrant ssh-config | grep HostName | awk '{print $2 }'`
ssh-keyscan $server >> /var/root/.ssh/known_hosts
#IP =`curl http://169.254.169.254/latest/meta-data/public-ipv4`

#IP=`curl http://169.254.169.254/latest/meta-data/public-ipv4`

WAR=OneClickDemo-0.0.1-SNAPSHOT.war
FOLDER=OneClickDemo-0.0.1-SNAPSHOT

(
echo "#!/bin/bash"

echo "cd /var/lib/tomcat7/webapps/"
echo "sudo mkdir tmp"
echo "sudo touch /opt/flag"
echo "sudo apt-get install unzip -yq"
echo "cd /var/www/html"
echo "sudo rm -rf archive.zip archive"
echo "sudo wget -c --auth-no-challenge --http-user=admin --http-password=$API_TOKEN http://$IP/job/OneClickDeployment/job/Build_Job3/lastSuccessfulBuild/artifact/target/$WAR"



echo "sudo cp -r $WAR /var/lib/tomcat7/webapps/tmp/"

echo "sudo rm -rf /var/lib/tomcat7/webapps/ROOT/*"

echo "sudo cp /var/lib/tomcat7/webapps/tmp/$WAR /var/lib/tomcat7/webapps/"
echo "sudo service tomcat7 restart"
echo "sudo cp -R /var/lib/tomcat7/webapps/$FOLDER/* /var/lib/tomcat7/webapps/ROOT/"
echo "sudo rm -rf /var/lib/tomcat7/webapps/tmp /var/lib/tomcat7/webapps/$WAR"
) >> /Users/Shared/Jenkins/deploy.sh
sudo chmod +x /Users/Shared/Jenkins/deploy.sh

ssh -i /Users/Shared/Jenkins/.ssh/vagrant_aws.pem ubuntu@$server 'bash -s' < /Users/Shared/Jenkins/deploy.sh
'''
      )
    }
triggers {
        upstream('Infra_Test_Job5', 'SUCCESS')
    }

}



