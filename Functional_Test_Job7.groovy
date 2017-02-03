freeStyleJob('OneClickDeployment/Functional_Test_Job7') {  
    
steps {
shell(''' 
#!/bin/sh
home=/Users/Shared/Jenkins
phantomjs=/Users/Shared/Jenkins/Home/workspace/OneClickDeployment/DeployCode_Job6/phantomjs-2.1.1-macosx/bin
export PATH=$PATH:$phantomjs
python $phantomjs/../../test.py

'''
      )
	    
    }
    triggers {
        upstream('OneClickDeployment/DeployCode_Job6', 'SUCCESS')
    }


} 