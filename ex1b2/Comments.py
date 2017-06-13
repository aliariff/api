@app.route('/api/v1/comments', methods=['GET', 'POST'])
@json_response
def api_v1_comments():
    if request.method == 'GET':
        return { 'comments': [c.as_dict() for c in Comment.query] }
    elif request.method == 'POST':
        j = request.json()
        c = Comment(
            author = j.get("author"),
            message = j.get("message"),
            date = parse_datetime(j.get("date"))
        )

        c.validate()

        db_session.add(c.validate())
        db_session.commit()
        return { 'comment': c.as_dict() }

@app.route('/api/v1/comments/<int:comment_id>', methods=['GET', 'PUT', 'DELETE'])
@json_response
def api_v1_comments_single(comment_id):
    comment = Comment.query.filter(Comment.comment_id == comment_id).one()

    if request.method == 'GET':
        return { 'comment': comment.as_dict() }
    elif request.method == 'PUT':
        j = request.json()

        comment.replace(
            author = j.get("author"),
            message = j.get("message"),
            date = parse_datetime(j.get("date"))
        )

        comment.validate()
        db_session.commit()

        return { 'comment': comment.as_dict() }
    elif request.method == 'DELETE':
        db_session.delete(comment)
        return { 'status': 200 }
