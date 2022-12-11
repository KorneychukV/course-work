export const environment = {
  production: true,
//  restUrl: window.location.origin + '/api',
  lkUrl: '/study-service',
  orderUrl: '/order-service',
  adminUrl: '/admin-service',

  picUrl: window.location.origin,
  keycloakUrl: 'http://192.168.0.199:8082/auth',
//  keycloakUrl: window.location.origin + '/keycloak/auth',
  keycloak_realm: 'prof',
  keycloak_client_id: 'app-auth'
};
