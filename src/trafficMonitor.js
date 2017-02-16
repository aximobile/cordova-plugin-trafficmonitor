var argscheck = require('cordova/argscheck'),
    exec = require('cordova/exec');

var moduleExport = {};

moduleExport.setOptions = function(options, successCallback, failureCallback) {
	  if(typeof options === 'object') {
		  cordova.exec( successCallback, failureCallback, 'SMS', 'setOptions', [options] );
	  } else {
		  if(typeof failureCallback === 'function') {
			  failureCallback('options should be specified.');
		  }
	  }
	};

module.exports = moduleExport;
  