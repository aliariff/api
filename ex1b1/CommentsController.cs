using System;
using System.Collections.Generic;
using System.Data;
using System.Data.Entity;
using System.Data.Entity.Infrastructure;
using System.Linq;
using System.Net;
using System.Net.Http;
using System.Threading.Tasks;
using System.Web.Http;
using System.Web.Http.Description;
using Record_Exc.Models;

namespace Record_Exc.Controllers
{
    public class CommentController : ApiController
    {
        private Record_ExcContext db = new Record_ExcContext();

        // GET: api/Comments
        public IQueryable<String> GetComments()
        {
            return db.Comments;
        }

        // GET: api/Comments/5
        [ResponseType(typeof(Comment))]
        public async Task<IHttpActionResult> GetComment(int id)
        {
            Comment comment = await db.Comments.FindAsync(id);
            if (comment == null)
            {
                return NotFound();
            }

            return Ok(comment);
        }

        // PUT: api/Comments/5
        [ResponseType(typeof(void))]
        public async Task<IHttpActionResult> PutComment(int id, Comment nComment)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            /*if (id != comment.ID)
            {
                return BadRequest();
            }*/

            Comment comment = await db.Comments.FindAsync(id);
            if(nComment.Author != null && nComment.Author != "")
            {
                comment.Author = nComment.Author;
            }
            if (nComment.Message != null && nComment.Message != "")
            {
                comment.Message = nComment.Message;
            }

            db.Entry(comment).State = EntityState.Modified;

            try
            {
                await db.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!CommentExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return StatusCode(HttpStatusCode.NoContent);
        }

        // POST: api/Comments
        [ResponseType(typeof(Comment))]
        public async Task<IHttpActionResult> PostComment(Comment comment)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            comment.Date = new DateTime(2017, 6, 13, 20, 0, 0);

            db.Comments.Add(comment);
            await db.SaveChangesAsync();

            return CreatedAtRoute("DefaultApi", new { id = comment.ID }, comment);
        }

        // DELETE: api/Comments/5
        [ResponseType(typeof(Comment))]
        public async Task<IHttpActionResult> DeleteComment(int id)
        {
            Comment comment = await db.Comments.FindAsync(id);
            if (comment == null)
            {
                return NotFound();
            }

            db.Comments.Remove(comment);
            await db.SaveChangesAsync();

            return Ok(comment);
        }

        protected override void Dispose(bool disposing)
        {
            if (disposing)
            {
                db.Dispose();
            }
            base.Dispose(disposing);
        }

        private bool CommentExists(int id)
        {
            return db.Comments.Count(e => e.ID == id) > 0;
        }
    }
}
