module.exports = {
    matricula: {
        type:String,
        hashKey:true
    },
    password:String,
    nombre:String,
    aPaterno:String,
    aMaterno:String,
    correo:String,
    foto:String,
    materias: {
        type:Array,
        schema:[String]
    },
    calificaciones1:{
        type:Array,
        schema:[String]
    },
    calificaciones2:{
        type:Array,
        schema:[String]
    },
    calificaciones3:{
        type:Array,
        schema:[String]
    },

}

