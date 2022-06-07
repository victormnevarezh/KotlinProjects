const dynamoose = require('dynamoose');
const { v4 : uuid } = require('uuid');
const {StatusCodes} = require('http-status-codes');
const alumniSchema = require('../schemas/Alumni');
const respond = require('../http-utils/respond');
const endpointWrapper = require('../http-utils/endpointWrapper');


dynamoose.aws.sdk.config.update({region:'us-east-1'})

class AlumniController{
    constructor(app){
        this.app=app;
        this.initializeRoutes();
    }

    get alumni(){
        return dynamoose.model('alumni', alumniSchema, {create : true});
    }

    initializeRoutes(){
        this.app.get('/api/alumni/', endpointWrapper(this.getAllAlumni.bind(this)));
        this.app.get('/api/alumni/:matricula', endpointWrapper(this.getAlumni.bind(this)));
        this.app.post('/api/alumni/', endpointWrapper(this.addAlumni.bind(this)));
        this.app.put('/api/alumni/:matricula', endpointWrapper(this.updateAlumni.bind(this)));
    }


    async getAllAlumni(req,res){
        const alumni = await this.alumni.scan().exec();
        var jsonRes = '{"alumni":[]}'
        var objRes = JSON.parse(jsonRes) 
        alumni.forEach(element=>{
            console.log(element)
            objRes.alumni.push(element)
        })
        respond(res, StatusCodes.OK, objRes);
    }

    //GET alumni por matricula
    async getAlumni(req,res){
        const {matricula} = req.params;
        const alumni = await this.alumni.query('matricula').eq(matricula).exec();
        if(!alumni || alumni.length === 0){
            respond(res, StatusCodes.NOT_FOUND);
        }else{
            var jsonRes = '{"alumni":[]}'
            var objRes = JSON.parse(jsonRes) 
            alumni.forEach(element=>{
                console.log(element)
                objRes.alumni.push(element)
            })
            respond(res, StatusCodes.OK, objRes);
        }
    }

    //ADD alumni
    async addAlumni(req,res){
        const alumni = {
            matricula: req.body.matricula,
            ...req.body
        };
        await this.alumni.create(alumni);
        respond(res, StatusCodes.OK,alumni)
    }

    //UPDATE alumni
    async updateAlumni(req,res){
        const {matricula} = req.params;
        const alumni = await this.alumni.query('matricula').eq(matricula).exec();
        if(!alumni || alumni.length === 0){
            respond(res, StatusCodes.NOT_FOUND);
        }else{
            console.log(req.body)
            const alumniUpdt = await this.alumni.update({matricula}, req.body)
            respond(res, StatusCodes.OK, alumniUpdt);
        }
    }

    static mount(app){
        return new AlumniController(app);
    }


}

module.exports=AlumniController;
