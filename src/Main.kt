// Clase base Persona
open class Persona(
    val dni: String,
    val nombre: String,
    val apellido: String,
    val fechaNacimiento: String,
    val direccion: String,
    val ciudadProcedencia: String
)

// Clase Paciente que hereda de Persona
class Paciente(
    dni: String,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    direccion: String,
    ciudadProcedencia: String,
    val numeroHistoriaClinica: String,
    val sexo: String,
    val grupoSanguineo: String,
    val alergias: List<String>
) : Persona(dni, nombre, apellido, fechaNacimiento, direccion, ciudadProcedencia)

// Clase base Empleado que hereda de Persona
open class Empleado(
    dni: String,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    direccion: String,
    ciudadProcedencia: String,
    val codigoEmpleado: String,
    val horasExtras: Int,
    val fechaIngreso: String,
    val area: String,
    val cargo: String
) : Persona(dni, nombre, apellido, fechaNacimiento, direccion, ciudadProcedencia)

// Clase EmpleadoPorPlanilla que hereda de Empleado
open class EmpleadoPorPlanilla(
    dni: String,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    direccion: String,
    ciudadProcedencia: String,
    codigoEmpleado: String,
    horasExtras: Int,
    fechaIngreso: String,
    area: String,
    cargo: String,
    val salarioMensual: Int,
    val porcentajeExtra: Double
) : Empleado(dni, nombre, apellido, fechaNacimiento, direccion, ciudadProcedencia, codigoEmpleado, horasExtras, fechaIngreso, area, cargo)

// Clase EmpleadoEventual que hereda de Empleado
class EmpleadoEventual(
    dni: String,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    direccion: String,
    ciudadProcedencia: String,
    codigoEmpleado: String,
    horasExtras: Int,
    fechaIngreso: String,
    area: String,
    cargo: String,
    val honorariosPorHora: Double,
    val horasTotalesTrabajadas: Int,
    val fechaTerminoContrato: String
) : Empleado(dni, nombre, apellido, fechaNacimiento, direccion, ciudadProcedencia, codigoEmpleado, horasExtras, fechaIngreso, area, cargo)

// Clase Médico que hereda de EmpleadoPorPlanilla
class Medico(
    dni: String,
    nombre: String,
    apellido: String,
    fechaNacimiento: String,
    direccion: String,
    ciudadProcedencia: String,
    codigoEmpleado: String,
    horasExtras: Int,
    fechaIngreso: String,
    area: String,
    cargo: String,
    salarioMensual: Int,
    porcentajeExtra: Double,
    val especialidad: String,
    val servicio: String,
    val numeroConsultorio: String
) : EmpleadoPorPlanilla(dni, nombre, apellido, fechaNacimiento, direccion, ciudadProcedencia, codigoEmpleado, horasExtras, fechaIngreso, area, cargo, salarioMensual, porcentajeExtra)

// Clase CitaMedica para manejar citas médicas
class CitaMedica(
    val paciente: Paciente,
    val medico: Medico,
    val fecha: String,
    val hora: String,
    val servicio: String
)

// Listas para almacenar las instancias
val empleados = mutableListOf<Empleado>()
val pacientes = mutableListOf<Paciente>()
val medicos = mutableListOf<Medico>()
val citas = mutableListOf<CitaMedica>()

fun main() {
    while (true) {
        println("\n--- Menú Hospital ---")
        println("1. Registrar Empleado")
        println("2. Registrar Paciente")
        println("3. Registrar Médico")
        println("4. Registrar Cita Médica")
        println("5. Listar Médicos por Especialidad")
        println("6. Listar Pacientes Atendidos por Médico")
        println("7. Salir")
        print("Seleccione una opción: ")

        when (readln().toInt()) {
            1 -> registrarEmpleado()
            2 -> registrarPaciente()
            3 -> registrarMedico()
            4 -> registrarCitaMedica()
            5 -> listarMedicosPorEspecialidad()
            6 -> listarPacientesPorMedico()
            7 -> break
            else -> println("Opción no válida, por favor intente nuevamente.")
        }
    }
}

fun registrarEmpleado() {
    println("\n--- Registro de Empleado ---")
    print("Ingrese el DNI: ")
    val dni = readln()
    print("Ingrese el nombre: ")
    val nombre = readln()
    print("Ingrese el apellido: ")
    val apellido = readln()
    print("Ingrese la fecha de nacimiento: ")
    val fechaNacimiento = readln()
    print("Ingrese la dirección: ")
    val direccion = readln()
    print("Ingrese la ciudad de procedencia: ")
    val ciudadProcedencia = readln()
    print("Ingrese el código de empleado: ")
    val codigoEmpleado = readln()
    print("Ingrese el número de horas extras: ")
    val horasExtras = readln().toInt()
    print("Ingrese la fecha de ingreso: ")
    val fechaIngreso = readln()
    print("Ingrese el área: ")
    val area = readln()
    print("Ingrese el cargo: ")
    val cargo = readln()

    println("¿Es empleado por Planilla o Eventual? (P/E): ")
    when (readln()) {
        "P" -> {
            print("Ingrese el salario mensual: ")
            val salarioMensual = readln().toInt()
            print("Ingrese el porcentaje adicional por hora extra: ")
            val porcentajeExtra = readln().toDouble()
            val empleado = EmpleadoPorPlanilla(
                dni, nombre, apellido, fechaNacimiento, direccion, ciudadProcedencia,
                codigoEmpleado, horasExtras, fechaIngreso, area, cargo, salarioMensual, porcentajeExtra
            )
            empleados.add(empleado)
        }
        "E" -> {
            print("Ingrese los honorarios por hora: ")
            val honorariosPorHora = readln().toDouble()
            print("Ingrese el número de horas totales trabajadas: ")
            val horasTotalesTrabajadas = readln().toInt()
            print("Ingrese la fecha de término del contrato: ")
            val fechaTerminoContrato = readln()
            val empleado = EmpleadoEventual(
                dni, nombre, apellido, fechaNacimiento, direccion, ciudadProcedencia,
                codigoEmpleado, horasExtras, fechaIngreso, area, cargo, honorariosPorHora, horasTotalesTrabajadas, fechaTerminoContrato
            )
            empleados.add(empleado)
        }
        else -> println("Tipo de empleado no válido.")
    }
}

fun registrarPaciente() {
    println("\n--- Registro de Paciente ---")
    print("Ingrese el DNI: ")
    val dni = readln()
    print("Ingrese el nombre: ")
    val nombre = readln()
    print("Ingrese el apellido: ")
    val apellido = readln()
    print("Ingrese la fecha de nacimiento: ")
    val fechaNacimiento = readln()
    print("Ingrese la dirección: ")
    val direccion = readln()
    print("Ingrese la ciudad de procedencia: ")
    val ciudadProcedencia = readln()
    print("Ingrese el número de historia clínica: ")
    val numeroHistoriaClinica = readln()
    print("Ingrese el sexo: ")
    val sexo = readLine()!!
    print("Ingrese el grupo sanguíneo: ")
    val grupoSanguineo = readln()
    print("Ingrese los medicamentos a los que es alérgico (separados por comas): ")
    val alergias = readln().split(",").map { it.trim() }

    val paciente = Paciente(
        dni, nombre, apellido, fechaNacimiento, direccion, ciudadProcedencia,
        numeroHistoriaClinica, sexo, grupoSanguineo, alergias
    )
    pacientes.add(paciente)
}

fun registrarMedico() {
    println("\n--- Registro de Médico ---")
    print("Ingrese el DNI: ")
    val dni = readln()
    print("Ingrese el nombre: ")
    val nombre = readln()
    print("Ingrese el apellido: ")
    val apellido = readln()
    print("Ingrese la fecha de nacimiento: ")
    val fechaNacimiento = readln()
    print("Ingrese la dirección: ")
    val direccion = readln()
    print("Ingrese la ciudad de procedencia: ")
    val ciudadProcedencia = readln()
    print("Ingrese el código de empleado: ")
    val codigoEmpleado = readln()
    print("Ingrese el número de horas extras: ")
    val horasExtras = readln().toInt()
    print("Ingrese la fecha de ingreso: ")
    val fechaIngreso = readln()
    print("Ingrese el área: ")
    val area = readln()
    print("Ingrese el cargo: ")
    val cargo = readln()
    print("Ingrese el salario mensual: ")
    val salarioMensual = readln().toInt()
    print("Ingrese el porcentaje adicional por hora extra: ")
    val porcentajeExtra = readln().toDouble()
    print("Ingrese la especialidad: ")
    val especialidad = readln()
    print("Ingrese el servicio: ")
    val servicio = readln()
    print("Ingrese el número de consultorio: ")
    val numeroConsultorio = readln()

    val medico = Medico(
        dni, nombre, apellido, fechaNacimiento, direccion, ciudadProcedencia,
        codigoEmpleado, horasExtras, fechaIngreso, area, cargo, salarioMensual, porcentajeExtra,
        especialidad, servicio, numeroConsultorio
    )
    medicos.add(medico)
    empleados.add(medico) // Añadir a lista de empleados también
}

fun registrarCitaMedica() {
    println("\n--- Registro de Cita Médica ---")
    print("Ingrese el DNI del paciente: ")
    val dniPaciente = readln()
    val paciente = pacientes.find { it.dni == dniPaciente }

    if (paciente == null) {
        println("Paciente no encontrado.")
        return
    }

    print("Ingrese el código del médico: ")
    val codigoMedico = readln()
    val medico = medicos.find { it.codigoEmpleado == codigoMedico }

    if (medico == null) {
        println("Médico no encontrado.")
        return
    }

    print("Ingrese la fecha de la cita: ")
    val fecha = readln()
    print("Ingrese la hora de la cita: ")
    val hora = readln()
    print("Ingrese el servicio de la cita: ")
    val servicio = readln()

    val cita = CitaMedica(paciente, medico, fecha, hora, servicio)
    citas.add(cita)
}

fun listarMedicosPorEspecialidad() {
    println("\n--- Listado de Médicos por Especialidad ---")
    print("Ingrese la especialidad: ")
    val especialidad = readln()

    val medicosEspecialidad = medicos.filter { it.especialidad.equals(especialidad, ignoreCase = true) }

    if (medicosEspecialidad.isEmpty()) {
        println("No se encontraron médicos para la especialidad ingresada.")
    } else {
        medicosEspecialidad.forEach {
            println("Nombre: ${it.nombre} ${it.apellido}, Servicio: ${it.servicio}, Consultorio: ${it.numeroConsultorio}")
        }
    }
}

fun listarPacientesPorMedico() {
    println("\n--- Listado de Pacientes por Médico ---")
    print("Ingrese el código del médico: ")
    val codigoMedico = readln()
    val medico = medicos.find { it.codigoEmpleado == codigoMedico }

    if (medico == null) {
        println("Médico no encontrado.")
        return
    }

    val pacientesAtendidos = citas.filter { it.medico == medico }.map { it.paciente }

    if (pacientesAtendidos.isEmpty()) {
        println("No se encontraron pacientes atendidos por el médico con el código ingresado.")
    } else {
        pacientesAtendidos.forEach {
            println("Nombre: ${it.nombre} ${it.apellido}")
        }
    }
}
