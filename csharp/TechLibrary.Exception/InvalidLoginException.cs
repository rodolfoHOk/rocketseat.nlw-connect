using System.Net;

namespace TechLibrary.Exception;

public class InvalidLoginException : TechLibraryException
{
  public InvalidLoginException() : base(string.Empty) { }
  public override List<string> GetErrorMessages() => ["Email e/ou senha inválidos."];
  public override HttpStatusCode GetStatusCode() => HttpStatusCode.Unauthorized;
}
