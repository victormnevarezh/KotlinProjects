module.exports = {
    idMateria:Number,
    foto:String,
    nombre:{
        type:String,
        hashKey:true
    },
    descripcion:String,
    dias:{
        type:Array,
        schema:[String]
    },
    horas:{
        type:Array,
        schema:[String]
    },
    matriculados:{
        type:Array,
        schema:[String]
    }
}