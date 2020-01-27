
<!-- GLOBAL HEADER -->
<jsp:include page="common/header.jsp" />


<!-- COURSE COVER IMAGE -->
<img src="${pageContext.request.contextPath }/images/course_cover.jpg"
	height="500px" class="w-100" />


<!-- MAIN CONTENT OF THE PAGE -->
<div class="container">

	<div class="row">

		<!-- HOMEWORK COLUMN-->
		<div class="col">
			<h3 class="p-3">Homework</h3>

			<div class="row">
				<div class="col-sm-12">
					<div class="card mb-2">
						<div class="card-body">
							<h4 class="card-title pb-3">Countries</h4>
							<p class="card-text">
								<a class="btn btn-success btn-lg btn-block" href="country/add"
									role="button">ADD</a> <a
									class="btn btn-primary btn-lg btn-block"
									href="country/show-all" role="button">SHOW ALL</a>
							</p>
						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="card mb-2">
						<div class="card-body">
							<h4 class="card-title pb-3">Courses</h4>
							<p class="card-text">
							
							<form action="course/search" class="mb-5">
								<div class="form-group">
									<input type="text" class="form-control"
										placeholder="Course Name" name="name">
								</div>
								<button type="submit" class="btn btn-primary  btn-lg btn-block">SEARCH</button>
							</form>


							<a class="btn btn-success btn-lg btn-block" href="course/add"
								role="button">ADD</a> <a
								class="btn btn-primary btn-lg btn-block" href="course/show-all"
								role="button">SHOW ALL</a>
							</p>
						</div>
					</div>
				</div>

			</div>

		</div>

		<!-- COURSE OUTLINE COLUMN -->
		<div class="col">
			<h3 class="p-3">Course Modules</h3>

			<div class="row">
				<div class="col-sm-12">
					<div class="card mb-2">
						<div class="card-body">
							<span class="badge badge-success">Completed</span>
							<h5 class="card-title">Environment setup (4 Hrs)</h5>
							<p class="card-text">
							<ol>
								<li>Installing and configuring Java 11</li>
								<li>Installing and configuring Maven (Build tool)</li>
								<li>Installing Git (Version Control tool)</li>
								<li>Installing Intellij Idea (IDE)</li>
								<li>Introducing common git commands (if needed)</li>
								<li>Introduction to Maven</li>
								<li>Practice</li>
							</ol>
							</p>
						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="card mb-2">
						<div class="card-body">
							<span class="badge badge-success">Completed</span>
							<h5 class="card-title">Introduction to Servlet and JSP (4
								Hrs)</h5>
							<p class="card-text">
							<ol>
								<li>Introduction to Servlet and Servlet Container</li>
								<li>Configuring Servlet Application</li>
								<li>MVC architecture in Spring</li>
								<li>Implementing HttpServlet and working with different
									HTTP Methods</li>
								<li>View rendering with Servlet and JSP</li>
								<li>Simple form data handling with Servlet and JSP</li>
								<li>Practice</li>
							</ol>
							</p>
						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="card mb-2">
						<div class="card-body">
							<span class="badge badge-success">Completed</span>
							<h5 class="card-title">Spring HelloWorld Application (4 Hrs)</h5>
							<p class="card-text">
							<ol>
								<li>Downloading application server (Tomcat)</li>
								<li>Configuring tomcat server with Intellij Idea</li>
								<li>MVC architecture in Spring</li>
								<li>Introduction to Servlet & Dispatcher Servlet
									Configuration</li>
								<li>What is a Bean, how to work with it</li>
								<li>View Resolver Bean definition, working with different
									view resolvers.</li>
								<li>@Component and @Configuration annotations</li>
								<li>Practice</li>
							</ol>
							</p>

						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="card mb-2">
						<div class="card-body">
							<span class="badge badge-info">Ongoing</span>
							<h5 class="card-title">Accessing Form Data with Spring (4
								Hrs)</h5>
							<p class="card-text">
							<ol>
								<li>Introduction to Taglibs.</li>
								<li>Form Taglib, core Taglib, and other taglib examples</li>
								<li>Accessing the form data and rendering those data to
									page</li>
								<li>Storing the form data in a list based storage,
									rendering them in the page</li>
								<li>Practice</li>
							</ol>
							</p>

						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="card mb-2">
						<div class="card-body">
							<span class="badge badge-secondary">Awaiting</span>
							<h5 class="card-title">Making a simple CRUD (4 Hrs)</h5>
							<p class="card-text">
							<ol>
								<li>Installing Postgresql locally</li>
								<li>Integrating JDBC Template to make a CRUD (Connecting
									with a PSql DB)</li>
								<li>Opening a Heroku account and creating a Postgresql DB
									in Heroku</li>
								<li>Setting up different profiles (Dev, QA, Prod etc)</li>
								<li>Practice</li>
							</ol>
							</p>

						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="card mb-2">
						<div class="card-body">
							<span class="badge badge-secondary">Awaiting</span>
							<h5 class="card-title">Making advanced CRUD (8 Hrs)</h5>
							<p class="card-text">
							<ol>
								<li>Integrating and configuring Hibernate Session Factory,
									Transaction Managers etc.</li>
								<li>Performing the CRUD with Hibernate</li>
								<li>Using Criteria API</li>
								<li>Practice</li>
							</ol>
							</p>

						</div>
					</div>
				</div>

				<div class="col-sm-12">
					<div class="card mb-2">
						<div class="card-body">
							<span class="badge badge-success">Completed</span>
							<h5 class="card-title">Integrating a UI Template (4 Hrs)</h5>
							<p class="card-text">
							<ol>
								<li>Configuration to render Static contents</li>
								<li>Adding CSS, JS, images and other static contents</li>
								<li>Collecting data from Form and Rendering them into a
									page</li>
								<li>Practice</li>
							</ol>
							</p>

						</div>
					</div>
				</div>

			</div>

		</div>

	</div>

</div>


<!-- GLOBAL FOOTER -->
<jsp:include page="common/footer.jsp" />




