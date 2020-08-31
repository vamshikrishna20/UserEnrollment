<div class="container">
   <div class="panel panel-info">
        <!-- Default panel contents -->
        <div class="alert alert-success" role="alert" ng-if="ctrl.txMessage">{{ctrl.txMessage}}</div>
        <div class="alert alert-danger" role="alert" ng-if="ctrl.txErrorMessage">{{ctrl.txErrorMessage}}</div>
        <div class="panel-heading"><span class="lead">List of Users </span></div>
		<div class="panel-body">
			<div class="table-responsive">
		        <table class="table table-hover">
		            <thead>
		            <tr>
		                <th>ID</th>
		                <th>User Name</th>
		                <th>Date of Birth</th>
		                <th>Enrollment</th>
		                <th width="100"></th>
		                <th width="100"></th>
		                <th width="100"></th>
		                <th width="100"></th>
		            </tr>
		            </thead>
		            <tbody>
		            <tr ng-repeat="u in ctrl.getAllUsers()" ng-class="{'selected': u.id== idSelected}" class="{{u.activation==true ? 'selected' : 'unselected'}}" >
		                <td>{{u.id}}</td>
		                <td>{{u.userName}}</td>
		                <td>{{u.dob | date:'MM-dd-yyyy'}}</td>
		                <td>{{u.activation}}</td>
		                <td><button type="button" ng-click="ctrl.editUser(u.id)" class="btn btn-success custom-width">Edit</button></td>
		                <td><button type="button" ng-click="ctrl.removeUser(u.id)" class="btn btn-danger custom-width">Delete</button></td>
		            </tr>
		            </tbody>
		        </table>		
			</div>
		</div>
    </div>
    <div class="panel panel-info">
        <!-- Default panel contents -->
        <div class="panel-heading"><span class="lead">User </span></div>
		<div class="panel-body">
	        <div class="formcontainer">
	            <div class="alert alert-success" role="alert" ng-if="ctrl.successMessage">{{ctrl.successMessage}}</div>
	            <div class="alert alert-danger" role="alert" ng-if="ctrl.errorMessage">{{ctrl.errorMessage}}</div>
	            <form ng-submit="ctrl.submit()" name="myForm" class="form-horizontal">
	                <input type="hidden" ng-model="ctrl.User.id" />
	                <div class="row">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="userName">User Name</label>
	                        <div class="col-md-7">
	                            <input type="text" ng-model="ctrl.User.userName" id="userName" class="Username form-control input-sm" placeholder="Enter User Name" required ng-minlength="3"/>
	                        </div>
	                    </div>
	                </div>
	                <div class="row {{!ctrl.User.id ? 'ng-show' : 'ng-hide'}}">
	                    <div class="form-group col-md-12">
	                        <label class="col-md-2 control-lable" for="dob">Date Of Birth</label>
	                        <div class="col-md-7">
	                            <input type="date" ng-model="ctrl.User.dob" id="dob" class="form-control input-sm" />
	                        </div>
	                    </div>
	                </div>
	                
           
                   <div class="row">
	                    <div class="form-check col-md-12">
	                        <label class="col-md-2 form-check-labele input-sm" for="activation">Enrollment </label>
	                         <input type="checkbox" ng-model="ctrl.User.activation" id="activation" class="form-check-input" />
	                    </div>
	                </div>
	                <div class="row">
	                    <div class="form-actions floatRight">
	                        <input type="submit"  value="{{!ctrl.User.id ? 'Add' : 'Update'}}" class="btn btn-primary btn-sm" ng-disabled="myForm.$invalid || myForm.$pristine">
	                        <button type="button" ng-click="ctrl.reset()" class="btn btn-warning btn-sm" >Reset Form</button>
	                    </div>
	                </div>
	            </form>
    	    </div>
		</div>	
    </div>
</div>