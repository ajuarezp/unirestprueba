(function() {
    'use strict';

    angular
        .module('unirestpruebaApp')
        .controller('LoanDialogController', LoanDialogController);

    LoanDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Loan'];

    function LoanDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Loan) {
        var vm = this;

        vm.loan = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.loan.id !== null) {
                Loan.update(vm.loan, onSaveSuccess, onSaveError);
            } else {
                Loan.save(vm.loan, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('unirestpruebaApp:loanUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.fecha = false;

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
