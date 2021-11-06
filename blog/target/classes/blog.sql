select a.author_id,author_name,p.post_id,post_content,c.commentator_id,comment_content,comment_time from 
"Blog"."Comment" as c,
"Blog"."Author" as a,
"Blog"."Post" as p ,
"Blog"."Commentator" as cr
where
a.author_id = p.author_id and c.post_id = p.post_id and cr.commentator_id = c.commentator_id