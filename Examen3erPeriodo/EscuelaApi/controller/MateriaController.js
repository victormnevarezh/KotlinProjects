const dynamoose = require('dynamoose');
const { v4 : uuid } = require('uuid');
const {StatusCodes} = require('http-status-codes');
const materiaSchema = require('../schemas/Materia');
const respond = require('../http-utils/respond');
const endpointWrapper = require('../http-utils/endpointWrapper');


dynamoose.aws.sdk.config.update({region:'us-east-1'})

class MateriaController{
    constructor(app){
        this.app=app;
        this.initializeRoutes();
    }


    get materia(){
        return dynamoose.model('materia', materiaSchema, {create : true});
    }

    initializeRoutes(){
        this.app.get('/api/materia/', endpointWrapper(this.getAllMateria.bind(this)));
        this.app.get('/api/materia/:nombre', endpointWrapper(this.getMateria.bind(this)));
        this.app.post('/api/materia/', endpointWrapper(this.addMateria.bind(this)));
    }

    /*Metodos materia*/

    async getAllMateria(req,res){
        const materia = await this.materia.scan().exec();
        var jsonRes = '{"materia":[]}'
        var objRes = JSON.parse(jsonRes) 
        materia.forEach(element=>{
            console.log(element)
            objRes.materia.push(element)
        })
        respond(res, StatusCodes.OK, objRes);
    }

    //GET materia x nombre
    async getMateria(req,res){
        const {nombre} = req.params;
        const materia = await this.materia.query('nombre').eq(nombre).exec();
        if(!materia || materia.length === 0){
            respond(res, StatusCodes.NOT_FOUND);
        }else{
            var jsonRes = '{"materia":[]}'
            var objRes = JSON.parse(jsonRes) 
            materia.forEach(element=>{
                console.log(element)
                objRes.materia.push(element)
            })
            respond(res, StatusCodes.OK, objRes);
        }
    }

    //ADD materia
    async addMateria(req,res){
        const materia = {
            nombre: req.body.nombre,
            ...req.body
        };
        await this.materia.create(materia);
        respond(res, StatusCodes.OK,materia)
    }


    static mount(app){
        return new MateriaController(app);
    }


}

module.exports=MateriaController;
