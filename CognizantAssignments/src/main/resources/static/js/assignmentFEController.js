//AssignmentFEController.js

angular
.module('app').controller('AssignmentFEController', function ($scope, $location, $window, assignmentFEDataFactory) {
	var vm = this;
    var obj = {
    	      firstName: "",
    	      surName: "",
    	      issueCount: 0,
    	      dateOfBirth: new Date()
    	    };
    
	    $scope.fileContent = '';
	    $scope.processFile = function () {
	      var file = document.getElementById("myFile").files[0];
	      if (file) {
	        var fileReader = new FileReader();
	        fileReader.readAsText(file, "UTF-8");
	        fileReader.onload = function (evt) {
	            $scope.fileContent = fileReader.result;
	            console.log("fileContent [ " + $scope.fileContent + " ]");
	            var allTextLines = $scope.fileContent.split(/\r\n|\n/);
	    		var headers = allTextLines[0].split(',');
	            console.log("headers [ " + headers + " ] ");

	    		var lines = [];
	    		for ( var i = 1; i < allTextLines.length; i++) {
	    			// split content based on comma
	    			var data = allTextLines[i].split(',');
	    			if (data.length == headers.length) {
	    				obj = {
	    			    	      firstName: "",
	    			    	      surName: "",
	    			    	      issueCount: 0,
	    			    	      dateOfBirth: new Date()
	    			    	    };
	    			    
	    				obj.firstName = data[0].replace(/"/g,'');
	    				obj.surName = data[1].replace(/"/g,'');
	    				obj.issueCount = data[2].replace(/"/g,'');
	    				obj.dateOfBirth = data[3].replace(/"/g,'');
	    				lines.push(obj);
	    			}
	    		}
	    		assignmentFEDataFactory.set(lines);
	            console.log("lines [ " + lines + " ]");
			    $window.location.href = '/assignments/#!/assignments-fe-result';
	        }
	        fileReader.onerror = function (evt) {
	            $scope.fileContent = "error";
	        }
	      }
	    }	



});