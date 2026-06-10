def call(String project, String imageTag, String dockerhubuser) {

    withCredentials([
        usernamePassword(
            credentialsId: 'dockerHubCred',
            usernameVariable: 'DOCKER_USER',
            passwordVariable: 'DOCKER_PASS'
        )
    ]) {

        sh '''
        echo $DOCKER_PASS | docker login -u $DOCKER_USER --password-stdin
        '''

        sh "docker push ${dockerhubuser}/${project}:${imageTag}"
    }
}
