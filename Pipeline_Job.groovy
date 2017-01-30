buildPipelineView('OCD')
 {
    filterBuildQueue()
    filterExecutors()
    title('One Click Deployment CI Pipeline')
    displayedBuilds(1)
    selectedJob('OneClickDeployment/compile_Job1')
    alwaysAllowManualTrigger()
    showPipelineParameters()
    refreshFrequency(60)
 }