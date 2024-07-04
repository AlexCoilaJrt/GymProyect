export const END_POINTS = {
  oauth: {
    register: 'auth/register',
    login: 'auth/login',
    logout: 'auth/logout',
    signup: 'auth/register',
    active: 'auth/active',
    create: 'auth/create',
    find: 'find',
    reset: 'auth/reset',
  },
  setup: {
    role: 'rol',
      client: 'clientegym',
    accesRole: 'acceso-rol',
    person: 'persona',
    institution: 'institucion',
    subsidiary: 'filial',
    faculty: 'facultad',
    prefessionalSchool: 'escuela-profesional',
    cycle: 'ciclo',
    group: 'grupo',
    jerarquia: "gerarquia",
    menu:"menu",
    users: 'auth/users',
    access:'acceso',
    access_parent:'acceso-padres',
      instructor: 'instructor',  // Asegúrate de que este endpoint está correctamente definido en tu API
      producto: 'producto', // Agregar este endpoint
      categoria: 'categoria', // Asegúrate de que también tienes el endpoint para categoría
      trainer: 'trainer', // Asegúrate de que también tienes el endpoint para categoría
  },

  event: {
    event: 'evento',
  },
  attendance: {
    attendance: 'asistencia',
  },
  /*****---------- */
  userModules: 'user-modules',
  validTokensOauth: 'valid-tokens-oauth',
  orders: 'messages',
};
