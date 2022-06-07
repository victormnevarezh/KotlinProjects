const {StatusCodes} = require('http-status-codes');
const respond = require('./respond');

function endpointWrapper(handler){
    return async (req,res)=>{
        try {
            await handler(req,res)
        } catch (e) {
            console.error('Error:',e)
            respond(res, StatusCodes.INTERNAL_SERVER_ERROR)
        }
    }
}

module.exports = endpointWrapper;