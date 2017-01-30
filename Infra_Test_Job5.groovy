freeStyleJob('OneClickDeployment/Infra_Test_Job5') {  
    
steps {
shell(''' 
#!/bin/sh
export HOME=$home

cd $work_space
sudo rspec
'''
      )
	    
    }
    triggers {
        upstream('Deploy_Job4', 'SUCCESS')
    }

} 