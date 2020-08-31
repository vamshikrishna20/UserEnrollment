'use strict';

angular.module('userApp').controller('UserController',
    ['UserService', '$scope',  function( UserService, $scope) {

        var self = this;
        self.User = {};
        self.Users=[];

        self.submit = submit;
        self.getAllUsers = getAllUsers;
        self.createUser = createUser;
        self.updateUser = updateUser;
        self.removeUser = removeUser;
        self.editUser = editUser;
        self.reset = reset;
        self.countUpdate = countUpdate;

        self.successMessage = '';
        self.errorMessage = '';
        self.done = false;

        self.onlyIntegers = /^\d+$/;
        self.onlyNumbers = /^\d+([,.]\d+)?$/;
 
        function submit() {
            console.log('Submitting');
            if (self.User.id === undefined || self.User.id === null) {
                console.log('Saving New User', self.User);
                createUser(self.User);
            } else {
                updateUser(self.User, self.User.id);
                console.log('User updated with id ', self.User.id);
            }
        }

        function createUser(User) {
            console.log('About to create User');
            UserService.createUser(User)
                .then(
                    function (response) {
                        console.log('User created successfully');
                        self.successMessage = 'User created successfully';
                        self.errorMessage='';
                        self.done = true;
                        self.User={};
                        $scope.myForm.$setPristine();
                    },
                    function (errResponse) {
                        console.error('Error while creating User');
                        self.errorMessage = 'Error while creating User: ' + errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }


        function updateUser(User, id){
            console.log('About to update User');
            UserService.updateUser(User, id)
                .then(
                    function (response){
                        console.log('User updated successfully');
                        self.successMessage='User updated successfully';
                        self.errorMessage='';
                        self.done = true;
                        $scope.myForm.$setPristine();
                    },
                    function(errResponse){
                        console.error('Error while updating User');
                        self.errorMessage='Error while updating User '+errResponse.data.errorMessage;
                        self.successMessage='';
                    }
                );
        }

        function countUpdate(id,txType)
        {
        	 console.log('About to update User stock');
             UserService.countUpdate(id,txType)
                 .then(
                     function (response){
                         console.log('User updated successfully');
                         self.txMessage='User updated successfully';
                         self.txErrorMessage='';
                         self.done = true;
                         $scope.myForm.$setPristine();
                     },
                     function(errResponse){
                         console.error('Out of stock');
                         self.txMessage='';
                         self.txErrorMessage=errResponse.data.errorMessage;
                     }
                 );
        }

        function removeUser(id){
            console.log('About to remove User with id '+id);
            UserService.removeUser(id)
                .then(
                    function(){
                        console.log('User '+id + ' removed successfully');
                    },
                    function(errResponse){
                        console.error('Error while removing User '+id +', Error :'+errResponse.data.errorMessage);
                    }
                );
        }


        function getAllUsers(){
            return UserService.getAllUsers();
        }
        
        $scope.idSelected = null;
        function editUser(id) {
            self.successMessage='';
            self.errorMessage='';
            $scope.idSelected = id;
            UserService.getUser(id).then(
                function (User) {
                    self.User = User;
                },
                function (errResponse) {
                    console.error('Error while removing User ' + id + ', Error :' + errResponse.data.errorMessage);
                }
            );
        }
        function reset(){
            self.successMessage='';
            self.errorMessage='';
            self.User={};
            $scope.myForm.$setPristine(); //reset Form
        }
    }


    ]);