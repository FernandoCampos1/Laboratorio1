# Laboratorio 11
Laboratorio-Iron

# Diseño y simulación de arquitecturas de nube de AWS

# Parte 1: Diseño de infraestructura en la nube

* Tarea:

- Diseñar una infraestructura en la nube para una aplicación web escalable.
- Incluya componentes como instancias informáticas, almacenamiento y configuraciones de red.
- Utilice AWS EC2, S3 y VPC para crear la arquitectura básica.


Para diseñar una infraestructura en la nube escalable para una aplicación web utilizando AWS, necesitaríamos varios componentes clave. Aquí está un plan de alto nivel:

1. **Instancias de EC2**: Estas serán las máquinas virtuales que ejecutan nuestra aplicación. Podríamos configurar un grupo de Auto Scaling para manejar el escalado automático de estas instancias basado en la demanda.

2. **Almacenamiento S3**: Utilizaremos S3 para almacenar cualquier dato estático o multimedia que nuestra aplicación necesite. S3 es altamente duradero y escalable, lo que lo hace ideal para este propósito.

3. **VPC**: Una VPC (Virtual Private Cloud) proporcionará un entorno de red aislado para nuestra aplicación. Dentro de la VPC, podemos configurar subredes, tablas de rutas y control de acceso para controlar el tráfico hacia y desde nuestras instancias de EC2.

4. **Balanceador de carga**: Un balanceador de carga distribuirá el tráfico entrante entre nuestras instancias de EC2 para asegurar que ninguna instancia se sobrecargue.

5. **Base de datos RDS**: Para almacenar los datos dinámicos de nuestra aplicación, utilizaremos una base de datos relacional en RDS (Relational Database Service). RDS facilita la configuración, operación y escalado de bases de datos relacionales en la nube.

Aquí está un diagrama básico de cómo podrían interactuar estos componentes:

```
Internet
    |
  Load Balancer
    |
  ---------------------
  |     |     |     |
 EC2   EC2   EC2   EC2  ... (Auto Scaling Group in VPC)
  |     |     |     |
  ---------------------
            |
          RDS Database
            |
          S3 Bucket
```

Se podría querer añadir un servicio de caché como ElastiCache para mejorar el rendimiento si se requeire, o utilizar servicios adicionales para manejar la autenticación, la búsqueda, etc.



# Parte 2: Configuración de IAM

* Tarea:
- Defina roles y políticas de IAM para diferentes componentes de la arquitectura, como desarrolladores, administradores y servidores de aplicaciones.
- Asegúrese de que cada rol se adhiera al principio de privilegio mínimo.



Para definir roles y políticas de IAM para diferentes componentes de la arquitectura, puedes seguir los siguientes pasos:

1. **Crea un grupo para los desarrolladores**: Este grupo tendrá permisos para realizar tareas de desarrollo, como lanzar nuevas instancias EC2, acceder a S3 para cargar y descargar archivos, y acceder a RDS para realizar tareas de base de datos.

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": [
        "ec2:*",
        "s3:*",
        "rds:*"
      ],
      "Resource": "*"
    }
  ]
}
```

2. **Crea un grupo para los administradores**: Este grupo tendrá permisos completos en todos los servicios de AWS. Sin embargo, es importante tener en cuenta que este nivel de acceso debe otorgarse con precaución debido a su alcance.

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "*",
      "Resource": "*"
    }
  ]
}
```

3. **Crea un rol para las instancias de EC2**: Este rol permitirá a las instancias de EC2 acceder a otros servicios de AWS. Por ejemplo, puedes permitir que las instancias de EC2 accedan a S3 para leer y escribir datos.

```json
{
  "Version": "2012-10-17",
  "Statement": [
    {
      "Effect": "Allow",
      "Action": "s3:*",
      "Resource": "*"
    }
  ]
}
```

Estos son solo ejemplos básicos. Las políticas de IAM deben personalizarse para satisfacer las necesidades específicas de tu arquitectura y deben revisarse regularmente para garantizar que siguen el principio de privilegio mínimo.

# Parte 3: Estrategia de gestión de recursos

* Tarea:

- Desarrolle una estrategia para administrar recursos que incluya escalado automático, equilibrio de carga y optimización de costos mediante AWS Auto Scaling, ELB y AWS Budgets.


Para administrar recursos de manera eficiente en AWS, puedes utilizar AWS Auto Scaling, Elastic Load Balancing (ELB) y AWS Budgets. Aquí está una estrategia que podrías seguir:

1. **AWS Auto Scaling**: Configura AWS Auto Scaling para tus instancias EC2. AWS Auto Scaling ajustará automáticamente el número de instancias EC2 en función de las condiciones que definas, como la utilización de la CPU o la demanda de red. Esto te permitirá manejar picos de tráfico sin intervención manual y también te ayudará a reducir los costos durante los períodos de baja demanda al reducir el número de instancias EC2.

2. **Elastic Load Balancing (ELB)**: Configura un balanceador de carga ELB para distribuir el tráfico entrante entre tus instancias EC2. ELB puede detectar la salud de tus instancias y distribuir el tráfico solo a las instancias saludables. Esto mejorará la disponibilidad y la tolerancia a fallos de tu aplicación.

3. **AWS Budgets**: Configura AWS Budgets para rastrear tus costos y uso de AWS. Puedes establecer presupuestos para tus costos totales o para costos específicos, como EC2 o S3, y recibir alertas cuando tus costos o uso excedan tus presupuestos. Esto te ayudará a mantener tus costos bajo control.

Aquí está un ejemplo de cómo podrías configurar AWS Auto Scaling y ELB:

```bash
# Crea un grupo de Auto Scaling
aws autoscaling create-auto-scaling-group --auto-scaling-group-name my-asg \
  --launch-configuration-name my-launch-config --min-size 1 --max-size 5 --desired-capacity 3 \
  --vpc-zone-identifier "subnet-0bb1c79de3EXAMPLE"

# Crea un balanceador de carga
aws elbv2 create-load-balancer --name my-load-balancer --subnets subnet-0bb1c79de3EXAMPLE

# Registra el grupo de Auto Scaling con el balanceador de carga
aws autoscaling attach-load-balancer-target-groups --auto-scaling-group-name my-asg \
  --target-group-arns arn:aws:elasticloadbalancing:us-west-2:123456789012:targetgroup/my-load-balancer/6d0ecf831eec9f09
```

Para AWS Budgets, puedes configurarlo a través de la consola de AWS. Ve a la sección AWS Budgets y sigue los pasos para crear un nuevo presupuesto.


# Parte 4: Implementación teórica

- Utilizando los servicios de AWS identificados, describa la arquitectura de la aplicación web. Describir cómo cada componente interactúa con los demás, centrándose en el flujo de datos y el control entre servicios. Esta descripción debe detallar el papel de cada servicio en la arquitectura, asegurando una comprensión clara de sus interacciones y dependencias.


La arquitectura de la aplicación web en AWS se compone de varios servicios que interactúan entre sí:

1. **Instancias de EC2**: Amazon EC2 proporciona la capacidad de cómputo para la aplicación. Las instancias de EC2 ejecutan la aplicación web y procesan las solicitudes de los usuarios. Cada instancia de EC2 se encuentra dentro de una VPC para un aislamiento de red seguro.

2. **Auto Scaling**: AWS Auto Scaling se utiliza para ajustar automáticamente el número de instancias de EC2 en función de la demanda de la aplicación. Cuando la demanda aumenta, Auto Scaling lanza más instancias de EC2 para manejar el tráfico adicional. Cuando la demanda disminuye, Auto Scaling termina las instancias innecesarias para reducir los costos.

3. **Elastic Load Balancing (ELB)**: ELB distribuye el tráfico entrante entre las instancias de EC2. Cuando una solicitud llega a la aplicación, ELB selecciona una instancia de EC2 basándose en criterios como la menor latencia o la menor carga de trabajo. Esto asegura que ninguna instancia de EC2 se sobrecargue y que la aplicación sea altamente disponible.

4. **Base de datos RDS**: Amazon RDS se utiliza para almacenar los datos dinámicos de la aplicación. Las instancias de EC2 interactúan con RDS para leer y escribir datos en la base de datos. RDS maneja tareas como la replicación de datos, la creación de copias de seguridad y la detección de fallos para garantizar que los datos estén siempre disponibles y seguros.

5. **Almacenamiento S3**: Amazon S3 se utiliza para almacenar datos estáticos o multimedia, como imágenes o archivos CSS/JS. Las instancias de EC2 pueden leer estos archivos de S3 cuando sea necesario. S3 proporciona almacenamiento duradero y altamente disponible para estos archivos.

El flujo de datos y control entre estos servicios es el siguiente:

- Cuando un usuario hace una solicitud a la aplicación, la solicitud llega al balanceador de carga ELB.
- ELB distribuye la solicitud a una de las instancias de EC2.
- La instancia de EC2 procesa la solicitud. Si necesita leer o escribir datos en la base de datos, interactúa con RDS. Si necesita leer un archivo estático, lo lee de S3.
- Una vez que la instancia de EC2 ha procesado la solicitud, envía la respuesta de vuelta al usuario a través de ELB.

Cada uno de estos servicios juega un papel crucial en la arquitectura de la aplicación y su interacción asegura que la aplicación sea escalable, altamente disponible y segura.


# Parte 5: Discusión y Evaluación

* Puntos de discusión:

- Explique la elección de servicios y cómo interactúan para proporcionar una infraestructura resistente y segura.
- Analice cómo las políticas de IAM diseñadas contribuyen a la seguridad general.
- Revisar la estrategia de gestión de recursos para garantizar que cumpla con las necesidades de escalabilidad y rentabilidad.

La elección de los servicios de AWS y su interacción se basa en proporcionar una infraestructura resistente, segura y escalable para la aplicación web.

1. **Instancias de EC2**: Proporcionan la capacidad de cómputo para la aplicación. Cada instancia se encuentra dentro de una VPC para un aislamiento de red seguro, lo que mejora la seguridad.

2. **Auto Scaling**: Se utiliza para ajustar automáticamente el número de instancias de EC2 en función de la demanda de la aplicación. Esto permite manejar picos de tráfico y reducir los costos durante los períodos de baja demanda, lo que mejora la escalabilidad y la rentabilidad.

3. **Elastic Load Balancing (ELB)**: Distribuye el tráfico entrante entre las instancias de EC2, lo que asegura que ninguna instancia se sobrecargue y mejora la disponibilidad de la aplicación.

4. **Base de datos RDS**: Se utiliza para almacenar los datos dinámicos de la aplicación. RDS maneja tareas como la replicación de datos, la creación de copias de seguridad y la detección de fallos, lo que mejora la disponibilidad y la seguridad de los datos.

5. **Almacenamiento S3**: Se utiliza para almacenar datos estáticos o multimedia. S3 proporciona almacenamiento duradero y altamente disponible, lo que mejora la disponibilidad de los datos.

Las políticas de IAM diseñadas contribuyen a la seguridad general al adherirse al principio de privilegio mínimo. Esto significa que cada rol tiene solo los permisos que necesita para realizar sus tareas, y nada más. Por ejemplo, el grupo de desarrolladores tiene permisos para realizar tareas de desarrollo, pero no tiene permisos completos en todos los servicios de AWS. Esto limita el potencial de daño si las credenciales de un desarrollador se ven comprometidas.

La estrategia de gestión de recursos está diseñada para garantizar que la infraestructura pueda escalar para manejar picos de tráfico y ser rentable. AWS Auto Scaling se utiliza para ajustar automáticamente el número de instancias de EC2 en función de la demanda, lo que permite manejar picos de tráfico sin intervención manual. Además, durante los períodos de baja demanda, Auto Scaling reduce el número de instancias de EC2 para reducir los costos. AWS Budgets se utiliza para rastrear los costos y el uso de AWS y recibir alertas cuando los costos o el uso exceden los presupuestos establecidos, lo que ayuda a mantener los costos bajo control.
