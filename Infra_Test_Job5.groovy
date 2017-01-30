freeStyleJob('OneClickDeployment/Infra_Test_Job5') {  
    
steps {
shell(''' 
#!/bin/sh
home=/private/var/root
work_space=/Users/Shared/Jenkins/Home/workspace/OneClickDeployment/Deploy_Job4
export HOME=$home
export TARGET_HOST=default

cd $work_space
sudo rspec
'''
      )
	    
    }
    triggers {
        upstream('Deploy_Job4', 'SUCCESS')
    }

} 