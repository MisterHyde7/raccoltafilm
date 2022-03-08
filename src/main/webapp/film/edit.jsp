<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	 
	 	<!-- Common imports in pages -->
	 	<jsp:include page="../header.jsp" />
	   
	   <title>Modifica Film</title>
	 </head>
	   <body class="d-flex flex-column h-100">
	   
	   		<!-- Fixed navbar -->
	   		<jsp:include page="../navbar.jsp"></jsp:include>
	    
			
			<!-- Begin page content -->
			<main class="flex-shrink-0">
			  <div class="container">
			  
			  		<div class="alert alert-danger alert-dismissible fade show ${errorMessage==null?'d-none':'' }" role="alert">
					  ${errorMessage}
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-danger alert-dismissible fade show d-none" role="alert">
					  Esempio di operazione fallita!
					  <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
					<div class="alert alert-info alert-dismissible fade show d-none" role="alert">
					  Aggiungere d-none nelle class per non far apparire
					   <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close" ></button>
					</div>
			  
			  <div class='card'>
				    <div class='card-header'>
				        <h5>Modifica film</h5> 
				    </div>
				    <div class='card-body'>
		
							<h6 class="card-title">Modifica i campi di interesse</h6>
		
		
							<form method="post" action="ExecuteEditFilmServlet" class="row g-3" novalidate="novalidate">
							
								<c:set var="filmInPagina" value="${filmDaModificare}"></c:set>
								
								<input type="hidden" name="idFilm" value="${filmInPagina.id }">
							
								<div class="col-md-6">
									<label for="titolo" class="form-label">Titolo</label>
									<input type="text" name="titolo" id="titolo" class="form-control" placeholder="Inserire il titolo"  
										value="${filmInPagina.titolo }" required>
								</div>
								
								<div class="col-md-6">
									<label for="genere" class="form-label">Genere</label>
									<input type="text" name="genere" id="genere" class="form-control" placeholder="Inserire il genere"  
										value="${filmInPagina.genere }" required>
								</div>
							
								<div class="col-md-6">
									<label for="durata" class="form-label">Durata</label>
									<input type="number" class="form-control" name="durata" id="durata" placeholder="Inserire la durata" 
									value="${filmInPagina.minutiDurata }" required>
								</div>
								
								<div class="col-md-3">
									<label for="dataPubblicazione" class="form-label">Data di pubblicazione</label>
									<input class="form-control"  name="dataPubblicazione" id="dataPubblicazione" type="date" placeholder="dd/MM/yy" title="formato : gg/mm/aaaa" 
										value="${filmInPagina.dataPubblicazione }" required/>
								</div>
								
								<div class="col-md-3">
									<label for="regista" class="form-label">Regista </label>
								    <select class="form-select" id="regista" name="regista" required>
								    	<option value="" selected> - Selezionare - </option>
								    	<c:forEach items="${listaRegisti}" var="regista">
								      	<option value="regista${regista.id}" >${regista.nome}</option>
								      	</c:forEach>
								    </select>
								</div>
								
								
							<div class="col-12">
								<button type="submit" name="submit" value="submit" id="submit" class="btn btn-primary">Conferma</button>
							</div>
		
						</form>
  
				    
				    
					<!-- end card-body -->			   
				    </div>
				<!-- end card -->
				</div>		
					  
			    
			  <!-- end container -->  
			  </div>
			  
			</main>
			
			<!-- Footer -->
			<jsp:include page="../footer.jsp" />
	  </body>
</body>
</html>