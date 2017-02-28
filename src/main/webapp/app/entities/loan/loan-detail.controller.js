(function() {
    'use strict';

    angular
        .module('unirestpruebaApp')
        .controller('LoanDetailController', LoanDetailController);

    LoanDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Loan'];

    function LoanDetailController($scope, $rootScope, $stateParams, previousState, entity, Loan) {
        var vm = this;

        vm.loan = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('unirestpruebaApp:loanUpdate', function(event, result) {
            vm.loan = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
