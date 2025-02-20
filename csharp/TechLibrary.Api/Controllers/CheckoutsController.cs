using Microsoft.AspNetCore.Authorization;
using Microsoft.AspNetCore.Mvc;
using TechLibrary.Api.Services.LoggedUser;
using TechLibrary.Api.UseCases.Checkouts.Register;

namespace TechLibrary.Api.Controllers;

[ApiController]
[Route("[controller]")]
[Authorize]
public class CheckoutsController : ControllerBase
{
  [HttpPost]
  [Route("{bookId}")]
  public IActionResult BookCheckout(Guid bookId)
  {
    var loggedUserService = new LoggedUserService(HttpContext);
    var useCase = new RegisterBookCheckoutUseCase(loggedUserService);
    useCase.Execute(bookId);
    
    return NoContent();
  }
}
