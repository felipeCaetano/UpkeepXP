<?php

	$host = "localhost";
	$user = "upkeepuser";
	$password = "upkeeppass";
	$banco = "Upkeepdb";

	$email = $_POST['email'];	
	$senha = $_POST['senha'];
	
	//echo "$email $senha<br>";
	/*
	$mysqli_connection = new MySQLi("$host", "$user", "$password", "$banco");
	
	if($mysqli_connection->connect_error){
		   echo "Desconectado! Erro: " . $mysqli_connection->connect_error;
		   }
	else{
		   echo "Conectado!";
		   }
		   */
	
	$dbc = mysqli_connect("$host", "$user", "$password", "$banco") or die("Erro ao conectar ao banco de dados");
	//var_dump($dbc);
	
	$query="SELECT * FROM login WHERE  email='$email' AND senha='$senha'"
        or die("Query Error: Erro ao Consultar Banco de Dados");
    //$query = "SELECT * FROM login" or die("Query Error: Erro ao Consultar Banco de Dados");
    
    $result=  mysqli_query($dbc, $query);
    //var_dump($result);
    $row= mysqli_fetch_array($result);
	$auth=$row['email'];
	$login_id =$row['login_id'];
	
	/*
	 * Trecho colocado para teste na tabela de usuários
	 * deve retornar o nome, ultimo nome, acesso do usuário válido
	 * se login e senha funcioar
	 */
	
	if($auth!= Null){
		$query="SELECT * FROM users WHERE  login_id='$login_id'"
        or die("Query Error: Erro ao Consultar Banco de Dados");
        
		$result=  mysqli_query($dbc, $query);
		$row= mysqli_fetch_array($result);
		$first = $row['first_name'];
		$second = $row['second_name'];
		$acess = $row['acesso'];
		echo "login_ok, $first, $second, $auth, $acess";
	}
	else{
		echo "login_danied";
	}
		   
?>
