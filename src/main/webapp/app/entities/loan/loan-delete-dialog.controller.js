(function() {
    'use strict';

    angular
        .module('unirestpruebaApp')
        .controller('LoanDeleteController',LoanDeleteController);

    LoanDeleteController.$inject = ['$uibModalInstance', 'entity', 'Loan'];

    function LoanDeleteController($uibModalInstance, entity, Loan) {
        var vm = this;

        vm.loan = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Loan.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
