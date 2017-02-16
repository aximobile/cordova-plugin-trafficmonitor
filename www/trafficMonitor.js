var argscheck = require('cordova/argscheck'),
    exec = require('cordova/exec');

var moduleExport = {};

moduleExport.listReceived = function(filter, successCallback, failureCallback) {
	cordova.exec( successCallback, failureCallback, 'Traffic', 'listReceived', [ filter ] );
};

module.exports = moduleExport;
  