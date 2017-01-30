freeStyleJob('OneClickDeployment/Deploy_Job4') {
      
steps {
shell(''' 
#!/bin/sh
home2=/private/var/root
export HOME=$home2

sudo /usr/local/bin/vagrant up --provider=aws
sudo /usr/local/bin/vagrant provision
'''
      )
    }

}