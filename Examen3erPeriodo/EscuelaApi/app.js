const express = require('express');
const cors = require('cors');
const {json: jsonParser} = require('body-parser');
const AlumniController = require('./controller/AlumniController');
const MaestriController = require('./controller/MaestriController');
const MateriaController = require('./controller/MateriaController');
const app = express();

app.use(jsonParser());
app.use(cors());
AlumniController.mount(app);
MaestriController.mount(app);
MateriaController.mount(app);


module.exports = app;