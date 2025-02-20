using Microsoft.AspNetCore.Mvc;
using TechLibrary.Api.UseCases.Books.Filter;
using TechLibrary.Communication.Requests;
using TechLibrary.Communication.Responses;

namespace TechLibrary.Api.Controllers;

[ApiController]
[Route("[controller]")]
public class BooksController : ControllerBase
{
  [HttpGet("Filter")]
  [ProducesResponseType(typeof(ResponseBooksJson), StatusCodes.Status200OK)]
  public IActionResult Filter(int pageNumber, string? title)
  {
    var request = new RequestFilterBooksJson
    {
      PageNumber = pageNumber,
      Title = title
    };
    var useCase = new FilterBookUseCase();
    var result = useCase.Execute(request);

    return Ok(result);
  }
}
