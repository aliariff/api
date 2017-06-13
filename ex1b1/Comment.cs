using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.ComponentModel.DataAnnotations;
using System.ComponentModel.DataAnnotations.Schema;

namespace Record_Exc.Models
{
    public class Comment
    {
        [Key]
        [DatabaseGenerated(DatabaseGeneratedOption.Computed)]
        public int ID { get; set; }
        public char Author { get; set; }
        public string Message { get; set; }
        public DateTime Date { get; set; }
    }
}