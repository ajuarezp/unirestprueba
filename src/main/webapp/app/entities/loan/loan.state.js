(function() {
    'use strict';

    angular
        .module('unirestpruebaApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('loan', {
            parent: 'entity',
            url: '/loan?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'unirestpruebaApp.loan.home.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loan/loans.html',
                    controller: 'LoanController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }],
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loan');
                    $translatePartialLoader.addPart('global');
                    return $translate.refresh();
                }]
            }
        })
        .state('loan-detail', {
            parent: 'loan',
            url: '/loan/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'unirestpruebaApp.loan.detail.title'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/loan/loan-detail.html',
                    controller: 'LoanDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                translatePartialLoader: ['$translate', '$translatePartialLoader', function ($translate, $translatePartialLoader) {
                    $translatePartialLoader.addPart('loan');
                    return $translate.refresh();
                }],
                entity: ['$stateParams', 'Loan', function($stateParams, Loan) {
                    return Loan.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'loan',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('loan-detail.edit', {
            parent: 'loan-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan/loan-dialog.html',
                    controller: 'LoanDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Loan', function(Loan) {
                            return Loan.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan.new', {
            parent: 'loan',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan/loan-dialog.html',
                    controller: 'LoanDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                nombre: null,
                                monto: null,
                                banco: null,
                                fecha: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('loan', null, { reload: 'loan' });
                }, function() {
                    $state.go('loan');
                });
            }]
        })
        .state('loan.edit', {
            parent: 'loan',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan/loan-dialog.html',
                    controller: 'LoanDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Loan', function(Loan) {
                            return Loan.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan', null, { reload: 'loan' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('loan.delete', {
            parent: 'loan',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/loan/loan-delete-dialog.html',
                    controller: 'LoanDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Loan', function(Loan) {
                            return Loan.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('loan', null, { reload: 'loan' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
