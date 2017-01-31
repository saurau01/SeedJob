freeStyleJob('OneClickDeployment/DeployCode_Job6') {
      
steps {
shell(''' 
#!/bin/sh
export HOME=/root
work_space=/Users/Shared/Jenkins/Home/workspace/OneClickDeployment/Deploy_Job4
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
echo "sudo wget -c --auth-no-challenge --http-user=user --http-password=f187918eb498b083e145aaa7c127c226 http://$IP/jenkins/view/Pipe_Line/job/Build_job3/lastSuccessfulBuild/artifact/*zip*/archive.zip"

echo "sudo unzip archive.zip"
echo "sudo rm -rf archive.zip"
echo "sudo cp -r archive/target/* /var/lib/tomcat7/webapps/tmp/"
echo "sudo rm -rf archive"
echo "sudo rm -rf /var/lib/tomcat7/webapps/ROOT/*"

echo "sudo cp /var/lib/tomcat7/webapps/tmp/$WAR /var/lib/tomcat7/webapps/"
echo "sudo service tomcat7 restart"
echo "sudo cp -R /var/lib/tomcat7/webapps/$FOLDER/* /var/lib/tomcat7/webapps/ROOT/"
echo "sudo rm -rf /var/lib/tomcat7/webapps/tmp /var/lib/tomcat7/webapps/$WAR"
) >> deploy.sh
sudo chmod +x deploy.sh

ssh -i /Users/saurabhkumar/Downloads/vagrant_aws.pem ubuntu@$server 'bash -s' < deploy.sh
'''
      )
    }
triggers {
        upstream('Infra_Test_Job5', 'SUCCESS')
    }

}



