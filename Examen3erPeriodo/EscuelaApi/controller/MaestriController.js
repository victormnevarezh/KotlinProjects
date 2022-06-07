const dynamoose = require('dynamoose');
const { v4 : uuid } = require('uuid');
const {StatusCodes} = require('http-status-codes');
const maestriSchema = require('../schemas/Maestri');
const respond = require('../http-utils/respond');
const endpointWrapper = require('../http-utils/endpointWrapper');


dynamoose.aws.sdk.config.update({region:'us-east-1'})

class MaestriController{
    constructor(app){
        this.app=app;
        this.initializeRoutes();
    }

    get maestri(){
        return dynamoose.model('maestri', maestriSchema, {create : true});
    }

    initializeRoutes(){
        this.app.get('/api/maestri/', endpointWrapper(this.getAllMaestri.bind(this)));
        this.app.get('/api/maestri/:matricula', endpointWrapper(this.getMaestri.bind(this)));
        this.app.post('/api/maestri/', endpointWrapper(this.addMaestri.bind(this)));
        this.app.put('/api/maestri/:matricula', endpointWrapper(this.updateMaestri.bind(this)));
    }

    
    /*Metodos maestri*/

    async getAllMaestri(req,res){
        const maestri = await this.maestri.scan().exec();
        var jsonRes = '{"maestri":[]}'
        var objRes = JSON.parse(jsonRes) 
        maestri.forEach(element=>{
            console.log(element)
            objRes.maestri.push(element)
        })
        respond(res, StatusCodes.OK, objRes);
    }

    //GET maestri por matricula
    async getMaestri(req,res){
        const {matricula} = req.params;
        const maestri = await this.maestri.query('matricula').eq(matricula).exec();
        if(!maestri || maestri.length === 0){
            respond(res, StatusCodes.NOT_FOUND);
        }else{
            var jsonRes = '{"maestri":[]}'
            var objRes = JSON.parse(jsonRes) 
            maestri.forEach(element=>{
                console.log(element)
                objRes.maestri.push(element)
            })
            respond(res, StatusCodes.OK, objRes);
        }
    }

    //ADD maestri
    async addMaestri(req,res){
        const maestri = {
            idMaestri: req.body.matricula,
            ...req.body
        };
        await this.maestri.create(maestri);
        respond(res, StatusCodes.OK,maestri)
    }

    //UPDATE maestri
    async updateMaestri(req,res){
        const {matricula} = req.params;
        const maestri = await this.maestri.query('matricula').eq(matricula).exec();
        if(!maestri || maestri.length === 0){
            respond(res, StatusCodes.NOT_FOUND);
        }else{
            const maestriUpdt = await this.maestri.update({matricula}, req.body)
            respond(res, StatusCodes.OK, maestriUpdt);
        }
    }


    static mount(app){
        return new MaestriController(app);
    }


}

module.exports=MaestriController;
